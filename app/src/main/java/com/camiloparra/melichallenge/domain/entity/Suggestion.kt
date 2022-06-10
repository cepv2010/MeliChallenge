package com.camiloparra.melichallenge.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * All queries searching by the user are inserted according to this entity
 *
 * Created by Camilo Parra on 10/06/2022.
 */
@Entity
class Suggestion {

    @PrimaryKey(autoGenerate = true) var id: Int = 0
    @ColumnInfo lateinit var suggest: String

}