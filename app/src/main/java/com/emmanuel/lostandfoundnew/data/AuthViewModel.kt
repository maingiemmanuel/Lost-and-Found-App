
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.navigation.NavController
import com.emmanuel.lostandfoundnew.models.User
import com.emmanuel.lostandfoundnew.navigation.ROUTE_HOME
import com.emmanuel.lostandfoundnew.navigation.ROUTE_LOGIN
import com.google.firebase.auth.FirebaseAuth


class AuthViewModelFactory(private val navController: NavController, private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(navController, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class AuthViewModel(private val navController: NavController, private val context: Context) : ViewModel() {

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _loggedInUserEmail = MutableLiveData<String>()
    val loggedInUserEmail: LiveData<String> = _loggedInUserEmail

    fun signIn(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // User signed in successfully
                    navigateToHome()
                } else {
                    // Sign-in failed
                    Toast.makeText(context, "Sign-in failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun signUp(name: String, email: String, password: String, confPassword: String, contact: String) {
        if (password != confPassword) {
            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
//                    Registration successful
                    User(name, contact, email,password,confPassword, mAuth.currentUser!!.uid)

                    Toast.makeText(context, "Registration successful", Toast.LENGTH_SHORT).show()

                    navigateToLogin()
                } else {
                    // Registration failed
                    Toast.makeText(context, "Registration failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun logout() {
        mAuth.signOut()
        navigateToLogin()
    }

    fun isLoggedIn(): Boolean {
        return mAuth.currentUser != null
    }

    private fun navigateToHome() {
        navController.navigate(ROUTE_HOME)

    }

    private fun navigateToLogin() {
        navController.navigate(ROUTE_LOGIN)
    }
}
