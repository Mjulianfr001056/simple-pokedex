package org.bangkit.simplepokedex

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pokemon(
    val id: String,
    val name: String,
    val description: String,
    val photo: String,
    val height: String,
    val weight: String,
    val ability: String,
    val category: String,
    val type: List<String>,
    val weakness: List<String>
) : Parcelable
