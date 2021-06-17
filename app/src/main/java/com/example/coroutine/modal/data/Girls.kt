package com.example.coroutine.modal.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Girls(
  @PrimaryKey(autoGenerate= true)
  val id: Int = 0,
  var name: String
)