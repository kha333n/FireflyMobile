package xyz.hisname.fireflyiii.ui.notifications

import android.app.Notification
import android.content.Context
import android.content.ContextWrapper
import android.app.NotificationManager
import android.app.NotificationChannel
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import xyz.hisname.fireflyiii.R
import java.util.*


class NotificationUtils(base: Context) : ContextWrapper(base) {

    private var notificationManager: NotificationManagerCompat = NotificationManagerCompat.from(this)
    private val manager: NotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager


    fun showPiggyBankNotification(contextText: String, contextTitle: String) {
        val PIGGY_BANK_CHANNEL_ID = "xyz.hisname.fireflyiii.PIGGY_BANK"
        val PIGGY_BANK_CHANNEL_NAME = "Piggy Bank"
        val GROUP_ID = "xyz.hisname.fireflyiii.PIGGY_BANK"
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val piggybankChannel = NotificationChannel(PIGGY_BANK_CHANNEL_ID,
                    PIGGY_BANK_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT).apply {
                enableLights(true)
                description = "Shows Piggy Bank result"
                enableVibration(false)
                lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            }
            manager.createNotificationChannel(piggybankChannel)
        }
        val groupBuilder = NotificationCompat.Builder(this, PIGGY_BANK_CHANNEL_ID).apply {
            setContentText(contextText)
            setGroup(GROUP_ID)
            setSmallIcon(R.drawable.ic_sort_descending)
            setGroupSummary(true)
            setStyle(NotificationCompat.InboxStyle().setBigContentTitle(contextTitle).addLine(contextText))
            setGroupAlertBehavior(Notification.GROUP_ALERT_SUMMARY)
        }
        notificationManager.notify(createNotificationId(), groupBuilder.build())

    }

    fun showBillNotification(contextText: String, contextTitle: String){
        val BILL_CHANNEL_ID = "xyz.hisname.fireflyiii.BILL"
        val BILL_CHANNEL_NAME = "Bills"
        val GROUP_ID = "xyz.hisname.fireflyiii.BILL"
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val billChannel = NotificationChannel(BILL_CHANNEL_ID,
                    BILL_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT).apply {
                enableLights(true)
                description = "Shows adding of Bills result"
                enableVibration(false)
                lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            }
            manager.createNotificationChannel(billChannel)
        }
        val groupBuilder = NotificationCompat.Builder(this, BILL_CHANNEL_ID).apply {
            setContentText(contextText)
            setGroup(GROUP_ID)
            setSmallIcon(R.drawable.ic_calendar_blank)
            setGroupSummary(true)
            setStyle(NotificationCompat.InboxStyle().setBigContentTitle(contextTitle).addLine(contextText))
            setGroupAlertBehavior(Notification.GROUP_ALERT_SUMMARY)
        }
        notificationManager.notify(createNotificationId(), groupBuilder.build())
    }

    private fun createNotificationId(): Int{
        val random = Random()
        var i = random.nextInt(99 + 1)
        i += 1
        return i
    }
}