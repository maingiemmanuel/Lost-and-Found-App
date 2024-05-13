package com.emmanuel.lostandfoundnew.ui.theme.screens.claim2

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


fun fetchReportedFoundItems(database: FirebaseDatabase,onDataFetched: (List<ReportedFoundItem>) -> Unit) {

    val foundItemsRef = database.getReference("found_items")


    foundItemsRef.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val foundItems = mutableListOf<ReportedFoundItem>()
            for (snapshot in dataSnapshot.children) {
                val item = snapshot.getValue(ReportedFoundItem::class.java)
                item?.let { foundItems.add(it) }
            }
            onDataFetched(foundItems)
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Handle error
        }
    })
}

data class ReportedFoundItem(
    val id: Int = 0,
    val itemName: String = "",
    val description: String = "",
    val contactInfo: String = "",
    val date: String = "",
)

data class Notification(
    val id: String,
    val title: String,
    val message: String
)

// Function to send a notification to the owner
fun sendNotificationToOwner(item: ReportedFoundItem) {
    val database = Firebase.database
    val notificationsRef = database.getReference("notifications")

    // Create a unique ID for the notification
    val notificationId = "${item.id}_${System.currentTimeMillis()}"

    val notification = Notification(
        id = notificationId,
        title = item.itemName,
        message = "Congratulations! Your found '${item.itemName}' has been claimed. Please provide documents for verification."
    )

    // Save the notification in the database under the owner's ID
    notificationsRef.child(item.id.toString()).child(notificationId).setValue(notification)
}




@Composable
fun FoundItemScreen(navController: NavController) {
    val database = FirebaseDatabase.getInstance()
    var reportedFoundItems by remember { mutableStateOf<List<ReportedFoundItem>>(emptyList()) }

    LaunchedEffect(database) {
        fetchReportedFoundItems(database) { items ->
            reportedFoundItems = items
        }
    }

    // Display the fetched items in a LazyColumn
    LazyColumn {
        items(reportedFoundItems) { item ->
            ReportedFoundItemCard(item)
        }
    }
}

@Composable
fun ReportedFoundItemCard(item: ReportedFoundItem) {
    Text(text = "Title: ${item.itemName}")
    Text(text = "Name: ${item.description}")
    Text(text = "Phone: ${item.contactInfo}")
    Text(text = "Date: ${item.date}")
}




@Preview
@Composable
private fun FoundItemPrev() {
    FoundItemScreen(rememberNavController())
}