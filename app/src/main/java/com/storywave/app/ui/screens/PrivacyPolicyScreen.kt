package com.storywave.app.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyPolicyScreen(onNavigateBack: () -> Unit) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Privacy Policy", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            Text(
                text = "Last Updated: August 2026",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Text(
                text = "Thank you for using StoryWave. Your privacy is important to us. This Privacy Policy explains what information we collect, how we use it, how we protect it, and your rights when using our application.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "By using StoryWave, you agree to the practices described in this Privacy Policy.",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(16.dp))

            PolicySection(
                title = "1. Information We Collect",
                content = "Depending on the features you use, StoryWave may collect the following information:\n\n" +
                        "Personal Information\n" +
                        "When you create an account, we may collect:\n" +
                        "• Name or username\n" +
                        "• Email address\n" +
                        "• Encrypted password\n\n" +
                        "App Usage Information\n" +
                        "• Reading history\n" +
                        "• Favorite stories\n" +
                        "• Saved progress\n" +
                        "• App preferences\n\n" +
                        "Device Information\n" +
                        "• Device model\n" +
                        "• Android version\n" +
                        "• App version\n" +
                        "• Crash reports"
            )

            PolicySection(
                title = "2. How We Use Your Information",
                content = "We use your information to:\n" +
                        "• Create and manage your account\n" +
                        "• Authenticate users\n" +
                        "• Save reading progress\n" +
                        "• Maintain your favorites\n" +
                        "• Personalize your experience\n" +
                        "• Improve application performance\n" +
                        "• Fix bugs and technical issues\n" +
                        "• Respond to customer support requests"
            )

            PolicySection(
                title = "3. Information We Do Not Collect",
                content = "StoryWave does not intentionally collect government identification numbers, financial information, health information, or biometric information unless explicitly required by future features."
            )

            PolicySection(
                title = "4. Third-Party Services",
                content = "Depending on the version of the app, these services may include:\n" +
                        "• Firebase Authentication\n" +
                        "• Google Play Services\n" +
                        "• Google AdMob\n" +
                        "• Firebase Crashlytics\n" +
                        "• Firebase Analytics\n\n" +
                        "Each third-party service has its own Privacy Policy."
            )

            PolicySection(
                title = "5. Advertising",
                content = "If advertisements are displayed, they may be provided through Google AdMob. Advertising providers may collect limited device information to display relevant advertisements."
            )

            PolicySection(
                title = "6. Data Storage",
                content = "Your account information and reading progress may be stored securely using cloud services. We use industry-standard security measures to help protect your information."
            )

            PolicySection(
                title = "7. Data Sharing",
                content = "We do not sell your personal information. We may share information only when required by law or necessary to protect our legal rights."
            )

            PolicySection(
                title = "8. Data Retention",
                content = "We retain your information only for as long as necessary to provide the requested services, comply with legal obligations, and resolve disputes."
            )

            PolicySection(
                title = "9. Account Deletion",
                content = "Users can request deletion of their account. When an account is deleted, we will permanently remove or anonymize personal information unless legally required to retain it."
            )

            PolicySection(
                title = "10. Children's Privacy",
                content = "StoryWave is not specifically directed toward children. We do not knowingly collect personal information from children without appropriate consent."
            )

            PolicySection(
                title = "11. Your Rights",
                content = "You may have the right to access, correct, or delete your information, withdraw consent, or request a copy of your personal information."
            )

            PolicySection(
                title = "12. Changes to This Privacy Policy",
                content = "We may update this Privacy Policy from time to time. Significant changes will be noted by updating the 'Last Updated' date."
            )

            Spacer(modifier = Modifier.height(24.dp))
            Divider(color = MaterialTheme.colorScheme.surfaceVariant)
            Spacer(modifier = Modifier.height(24.dp))

            // Contact Us Section
            Text(
                text = "13. Contact Us",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "If you have any questions about this Privacy Policy or our privacy practices, please contact us:",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "Developer: StoryWave Team",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:nanipinnam@gmail.com")
                        putExtra(Intent.EXTRA_SUBJECT, "StoryWave Privacy Inquiry")
                    }
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Email, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimaryContainer)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Contact Support", color = MaterialTheme.colorScheme.onPrimaryContainer)
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "nanipinnam@gmail.com",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}

@Composable
fun PolicySection(title: String, content: String) {
    Column(modifier = Modifier.padding(vertical = 12.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = content,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            lineHeight = 20.sp
        )
    }
}
