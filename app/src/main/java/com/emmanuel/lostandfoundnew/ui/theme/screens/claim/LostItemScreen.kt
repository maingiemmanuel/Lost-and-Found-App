package com.emmanuel.lostandfoundnew.ui.theme.screens.claim

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.emmanuel.lostandfoundnew.navigation.ROUTE_ADD
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

data class ReportedLostItem(
    val itemName: String = "",
    val description: String = "",
    val contactInfo: String = "",
    val date: String = "",

)

fun fetchReportedLostItems(database: FirebaseDatabase,onDataFetched: (List<ReportedLostItem>) -> Unit) {
    val reportedLostItemsRef = database.getReference("reportedLostItems")

    reportedLostItemsRef.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val reportedLostItems = mutableListOf<ReportedLostItem>()
            for (snapshot in dataSnapshot.children) {
                val item = snapshot.getValue(ReportedLostItem::class.java)
                item?.let { reportedLostItems.add(it) }
            }
            onDataFetched(reportedLostItems)
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Handle error
        }
    })
}

@Composable
fun LostItemScreen(navController: NavController) {
    val database = FirebaseDatabase.getInstance()
    var reportedLostItems by remember { mutableStateOf<List<ReportedLostItem>>(emptyList()) }

    LaunchedEffect(database) {
        fetchReportedLostItems(database) { items ->
            reportedLostItems = items
        }
    }

    // Display the fetched items in a LazyColumn
    LazyColumn {
        items(reportedLostItems) { item ->
            ReportedLostItemCard(item)
        }
    }

    if (reportedLostItems.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Reported Lost Items")

        }
    }
}

@Composable
fun ReportedLostItemCard(item: ReportedLostItem) {
    // Display reported lost item details in a card
    Text(text = "Title: ${item.itemName}")
    Text(text = "Name: ${item.description}")
    Text(text = "Phone: ${item.contactInfo}")
    Text(text = "Date: ${item.date}")
}

@Preview
@Composable
private fun LostItemPrev() {
    LostItemScreen(rememberNavController())
}
