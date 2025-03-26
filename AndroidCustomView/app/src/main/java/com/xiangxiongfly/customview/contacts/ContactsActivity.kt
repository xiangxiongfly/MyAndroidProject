package com.xiangxiongfly.customview.contacts

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xiangxiongfly.core.base.BaseActivity
import com.xiangxiongfly.core.widgets.sideindexbar.SideIndexBar
import com.xiangxiongfly.customview.R
import java.util.*

class ContactsActivity : BaseActivity() {
    private lateinit var rvContacts: RecyclerView
    private lateinit var sideIndexBar: SideIndexBar
    private val contacts = mutableListOf<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        rvContacts = findViewById(R.id.rv_contacts)
        sideIndexBar = findViewById(R.id.side_index_bar)

        initData()

        val contactAdapter = ContactAdapter(contacts)
        rvContacts.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = contactAdapter
        }

        sideIndexBar.setOnIndexChangedListener(object : SideIndexBar.OnIndexChangedListener {
            override fun onIndexChanged(index: String) {
                Log.e("TAG", "onIndexChanged:${index}")
                for (i in contacts.indices) {
                    if (contacts[i].name.startsWith(index, ignoreCase = true)) {
                        rvContacts.scrollToPosition(i)
                        break
                    }
                }
            }

            override fun onIndexReleased() {
                Log.e("TAG", "onIndexReleased")
            }
        })
    }

    private fun initData() {
        for (i in 1..100) {
            contacts.add(Contact(generateRandomName()))
        }
        contacts.sortBy { it.name }
    }

    private fun generateRandomName(): String {
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z')
        val random = Random()
        val nameLength = random.nextInt(5) + 3 // 生成 3 到 7 个字符的名字
        return (1..nameLength).map { charPool[random.nextInt(charPool.size)] }.joinToString("")
    }

}