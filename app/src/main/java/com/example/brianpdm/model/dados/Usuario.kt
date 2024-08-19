package com.example.brianpdm.model.dados

import com.google.firebase.firestore.DocumentId

data class Usuario(

    @DocumentId
    var id: String="",
    val nome: String = "",
    val senha: String = ""
)