package com.example.dhammapada.ui.data

import org.json.JSONObject

data class DhammapadaRecord(
    val id: Int,
    val type: String,
    val date: String,
    val date_unixtime: String,
    val from: String?,
    val from_id: String,
    val photo: String?,
    val photo_file_size: Int?,
    val width: Int?,
    val height: Int?,
    val text: String,
) {
    companion object {
        fun fromJson(jsonObject: JSONObject): DhammapadaRecord {

            return DhammapadaRecord(
                jsonObject.getInt("id"),
                jsonObject.getString("type"),
                jsonObject.getString("date"),
                jsonObject.getString("date_unixtime"),
                jsonObject.optString("from"),
                jsonObject.getString("from_id"),
                jsonObject.optString("photo"),
                jsonObject.optInt("photo_file_size"),
                jsonObject.optInt("width"),
                jsonObject.optInt("height"),
                jsonObject.getString("text"),
            )
        }
    }
}
