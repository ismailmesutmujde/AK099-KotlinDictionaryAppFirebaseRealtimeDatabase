package com.ismailmesutmujde.kotlindictionaryappfirebaserealtimedatabase.model

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class Words (var word_id : String? = "" ,
                  var english : String? = "",
                  var turkish : String? = "") : Serializable {

}