package com.emmanuel.lostandfoundnew.data

import androidx.lifecycle.ViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


data class ReportData(
    val itemName: String,
    val description: String,
    val contactInfo: String,
    val date: String,
)


class ReportViewModel : ViewModel() {
    private val reportsRef = Firebase.database.getReference("reportedLostItems")

    fun addReport(reportData: ReportData) {
        // Push report data to Firebase
        val reportKey = reportsRef.push().key
        if (reportKey != null) {
            reportsRef.child(reportKey).setValue(reportData)
        }
    }
}

