package com.xiangxiongfly.customview.contacts

object ContactRepo {
    private val defaultComparator = Comparator<Contact> { a, b ->
        val aIsHash = a.groupName.startsWith("#")
        val bIsHash = b.groupName.startsWith("#")
        when {
            aIsHash && !bIsHash -> 1
            !aIsHash && bIsHash -> -1
            else -> a.groupName.compareTo(b.groupName)
        }
    }

    fun getGroupList(list: List<Contact>): MutableList<String> {
        val groupList = mutableListOf<String>()
        list.forEach {
            if (!groupList.contains(it.groupName)) {
                groupList.add(it.groupName)
            }
        }
        return groupList
    }

    fun sortContacts(
        list: List<Contact>, comparator: Comparator<Contact> = defaultComparator
    ): List<Contact> {
        return list.sortedWith(comparator)
    }

    fun generateContacts(): MutableList<Contact> {
        val contacts = mutableListOf<Contact>()
        contacts.add(Contact("AAAA"))
        contacts.add(Contact("BBBAAA"))
        contacts.add(Contact("BBBAAA"))
        contacts.add(Contact("#BBBAAA"))
        contacts.add(Contact("#1BBBAAA"))
        contacts.add(Contact("。2BBBAAA"))
        contacts.add(Contact("。2BBBAAA"))
        contacts.add(Contact("！2BBBAAA"))
        contacts.add(Contact("~2BBBAAA"))
        contacts.add(Contact("CCC"))
        contacts.add(Contact("小明1"))
        contacts.add(Contact("小明2"))
        contacts.add(Contact("小明2"))
        contacts.add(Contact("Tom"))
        contacts.add(Contact("Tom"))
        contacts.add(Contact("six"))
        contacts.add(Contact("six"))
        contacts.add(Contact("?ix"))
        contacts.add(Contact("Wix"))
        contacts.add(Contact("WGGix"))
        contacts.add(Contact("WGGix"))
        contacts.add(Contact("GGix"))
        contacts.add(Contact("six"))
        contacts.add(Contact("six"))
        contacts.add(Contact("six"))
        contacts.add(Contact("six"))
        contacts.add(Contact("Tom"))
        contacts.add(Contact("小明24"))
        contacts.add(Contact("小明24"))
        contacts.add(Contact("CCC"))
        contacts.add(Contact("CCC"))
        contacts.add(Contact("CCC"))
        contacts.add(Contact("BBBAAA"))
        contacts.add(Contact("AAAA"))
        contacts.add(Contact("Y"))
        contacts.add(Contact("AAAA"))
        contacts.add(Contact("XX"))
        contacts.add(Contact("XX"))
        contacts.add(Contact("Z"))
        contacts.add(Contact("Z"))
        return contacts
    }
}