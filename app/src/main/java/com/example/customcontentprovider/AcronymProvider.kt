package com.example.customcontentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri


class AcronymProvider: ContentProvider(){

    companion object{
        val PROVIDER_NAME = "com.example.customcontentprovider/AcronymProvider"
        val URL = "content://$PROVIDER_NAME/ACTABLE"
        var CONTENT_URI = Uri.parse(URL)

        val _ID = "_id"
        val NAME = "NAME"
        val MEANING = "MEANING"
    }

    lateinit var db : SQLiteDatabase

    override fun onCreate(): Boolean {
        var helper = MyHelper(getContext())
        db = helper.writableDatabase
        return (db!=null)
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        db.insert("ACTABLE",null,values)
        context?.contentResolver?.notifyChange(uri,null)
        return uri
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        var count = db.update("ACTABLE",values,selection,selectionArgs)
        context?.contentResolver?.notifyChange(uri,null)
        return count;

    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        var count = db.delete("ACTABLE",selection,selectionArgs)
        context?.contentResolver?.notifyChange(uri,null)
        return count;
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return db.query("ACTABLE",projection,selection,selectionArgs,null,null,sortOrder)
    }

    override fun getType(uri: Uri): String? {
        return "vnd.android.cursor.dir/vnd.example.actable"
    }



}