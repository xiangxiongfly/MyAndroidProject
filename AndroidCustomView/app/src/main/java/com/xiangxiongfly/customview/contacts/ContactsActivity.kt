package com.xiangxiongfly.customview.contacts

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xiangxiongfly.core.base.BaseActivity
import com.xiangxiongfly.core.widgets.sideindexbar.SideIndexBar
import com.xiangxiongfly.core.widgets.sideindexbar.SideIndexBar2
import com.xiangxiongfly.customview.R

class ContactsActivity : BaseActivity() {
    private lateinit var rvContacts: RecyclerView
    private lateinit var indexBar: SideIndexBar
    private lateinit var indexBar2: SideIndexBar2
    private lateinit var contactAdapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        rvContacts = findViewById(R.id.rv_contacts)
        indexBar = findViewById(R.id.index_bar)
        indexBar2 = findViewById(R.id.index_bar2)

        initRv()
        initIndexBar()
        initIndexBar2()
        initData()
    }

    private fun initRv() {
        contactAdapter = ContactAdapter()
        rvContacts.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = contactAdapter
        }
    }

    private fun initIndexBar() {
        indexBar.setOnIndexChangedListener(object : SideIndexBar.OnIndexChangedListener {
            override fun onIndexChanged(position: Int, letter: String) {
                Log.e("TAG", "onIndexChanged:${letter}")
                for (index in contactAdapter.items.indices) {
                    if (contactAdapter.items[index].groupName == letter) {
                        (rvContacts.layoutManager as LinearLayoutManager)
                            .scrollToPositionWithOffset(index, 0)
                        break
                    }
                }
            }

            override fun onIndexReleased() {
                Log.e("TAG", "onIndexReleased")
            }
        })
    }

    private fun initIndexBar2() {
        indexBar2.setOnIndexChangedListener(object : SideIndexBar2.OnIndexChangedListener {
            override fun onIndexChanged(position: Int, letter: String) {
                Log.e("TAG", "onIndexChanged:${letter}")
                val index = contactAdapter.items.indexOfFirst {
                    it.groupName == letter
                }
                if (index != -1) {
                    rvContacts.scrollToPosition(index)
                }
            }

            override fun onIndexReleased() {
                Log.e("TAG", "onIndexReleased")
            }
        })
    }

    private fun initData() {
        val list = ContactRepo.generateContacts()
        val newList = ContactRepo.sortContacts(list)
        contactAdapter.submitList(newList)

        val groupList = ContactRepo.getGroupList(newList)
        indexBar2.setLetters(groupList)
    }
}