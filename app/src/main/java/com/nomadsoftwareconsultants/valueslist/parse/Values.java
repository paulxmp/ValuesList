package com.nomadsoftwareconsultants.valueslist.parse;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

@ParseClassName("Values")
public class Values extends ParseObject {

    private static final String TAG = Values.class.getSimpleName();

    // getting Value name
    public String getValueName() {
        return getString("ValueName");
    }

    // setting Value name
    public void setValueName(String valueName) {
        put("ValueName", valueName);
    }

    // getting Value Description
    public String getValueDescription() {
        return getString("ValueDescription");
    }

    // setting Value Description
    public void setValueDescription(String valueDescription) {
        put("ValueDescription", valueDescription);
    }

    // getting Value Image File
    public ParseFile getValueImageFile() {
        return getParseFile("ValueImageFile");
    }

    // setting Value Image File
    public void setValueImageFile(ParseFile valueImageFile) {
        put("ValueImageFile", valueImageFile);
    }

    public static ParseQuery<Values> getQuery() {
        return ParseQuery.getQuery(Values.class);
    }
}
