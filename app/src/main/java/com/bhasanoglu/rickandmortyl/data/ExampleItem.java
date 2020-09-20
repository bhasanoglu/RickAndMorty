package com.bhasanoglu.rickandmortyl.data;

public class ExampleItem { //Anasayfada gösterilecek değerler
private String mImageUrl;
private String mName;
private String mStatus;
private String mLocName;
private String mSpecies;
private String mGender;

public ExampleItem(String imageurl, String name, String status, String locname, String species,String gender)
{                                                           //constructor ve get metodları karşıdan return ile çekilecek
    mImageUrl = imageurl;
    mName = name;
    mStatus = status;
    mLocName = locname;
    mSpecies = species;
    mGender = gender;
}
public String getmImageUrl(){
    return mImageUrl;
}

public  String getmName(){
    return  mName;
}

public  String getmStatus(){
    return mStatus;
}

public  String getmLocName(){
        return mLocName;
    }

public  String getmSpecies(){
        return mSpecies;
    }

public  String getmGender(){
        return mGender;
    }

}
