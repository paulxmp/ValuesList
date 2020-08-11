package com.nomadsoftwareconsultants.valueslist.ui.values;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nomadsoftwareconsultants.valueslist.R;
import com.nomadsoftwareconsultants.valueslist.adapters.ValuesCardviewAdapter;
import com.nomadsoftwareconsultants.valueslist.callbacks.ValuesListItemTouchHelperCallback;
import com.nomadsoftwareconsultants.valueslist.listeners.OnStartDragListener;
import com.nomadsoftwareconsultants.valueslist.parse.Values;
import com.parse.ParseFile;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ValuesFragment extends Fragment implements OnStartDragListener {

    private static final String TAG = ValuesFragment.class.getSimpleName();

    private ValuesViewModel valuesViewModel;
    private RecyclerView valuesCardview;
    private ItemTouchHelper mItemTouchHelper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        valuesViewModel = new ViewModelProvider(getActivity()).get(ValuesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_values, container, false);
        final TextView textView = root.findViewById(R.id.text_values);
        valuesCardview = root.findViewById(R.id.valuesCardview);

        valuesViewModel.getText().observe(getViewLifecycleOwner(), s -> textView.setText(s));

        valuesViewModel.getValues().observe(getViewLifecycleOwner(), values -> {
            if (values != null) {
                display(values);
            }
        });

        //addValueToParse();

        return root;
    }

    private void display(List<Values> valuesList) {
        ValuesCardviewAdapter valuesCardviewAdapter = new ValuesCardviewAdapter(getActivity(), valuesList, this);
        valuesCardview.setHasFixedSize(true);
        valuesCardview.setAdapter(valuesCardviewAdapter);
        valuesCardview.setLayoutManager(new LinearLayoutManager(getActivity()));
        ItemTouchHelper.Callback callback = new ValuesListItemTouchHelperCallback(valuesCardviewAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(valuesCardview);
    }

    private void addValueToParse() {
        Values values = new Values();
        values.setValueName("Honesty");
        values.setValueDescription("Always speak Truth to Power.");

        Drawable drawable = getResources().getDrawable(R.drawable.glide_place, null);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bitmapdata = stream.toByteArray();
        final ParseFile parseFile = new ParseFile("glide.jpg", bitmapdata);
        parseFile.saveInBackground((SaveCallback) e -> {
            values.setValueImageFile(parseFile);
            values.saveInBackground(e1 -> Toast.makeText(getActivity(), "Parse image upload success", Toast.LENGTH_SHORT).show());
        });
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
