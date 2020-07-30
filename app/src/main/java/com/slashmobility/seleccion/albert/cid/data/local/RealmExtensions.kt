package com.slashmobility.seleccion.albert.cid.data.local

import android.util.Log
import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.kotlin.deleteFromRealm
import java.lang.reflect.Method

fun Realm.deleteInCascade(objectClass: Class<out RealmObject>) {
    where(objectClass).findAll()?.forEach { realmObject ->
        realmObject.deleteChildrens()
        realmObject.deleteFromRealm()
    }
}

fun Realm.deleteInCascadeRealmModel(objectClass: Class<out RealmModel>) {

    where(objectClass).findAll()?.forEach { realmModel ->
        realmModel.deleteChildrens(this)
        realmModel.deleteFromRealm()
    }
}

private fun RealmObject.deleteChildrens() {
    val methodList = javaClass.superclass?.declaredMethods
    methodList?.forEach { method ->
        try {
            if (method.isNotGetter()) return@forEach
            if (method.isPrimitive()) return@forEach
            when {
                method.isRealmObject() -> removeMethodAsRealmObject(method)
                method.isRealmClass() -> removeMethodAsRealmClass(method)
            }
        } catch (ex: Exception) {
            Log.e("RealmDelete", ex.message.orEmpty())
        }
    }
}

private fun RealmModel.deleteChildrens(realm: Realm) {
    val methodList = javaClass.superclass?.declaredMethods
    methodList?.forEach { method ->
        try {
            if (method.isNotGetter()) return@forEach
            if (method.isPrimitive()) return@forEach
            when {
                method.isRealmObject() -> removeMethodAsRealmObject(realm, method)
                method.isRealmClass() -> removeMethodAsRealmClass(realm, method)
            }
        } catch (ex: Exception) {
            Log.e("RealmDelete", ex.message.orEmpty())
        }
    }
}

private fun RealmObject.removeMethodAsRealmObject(method: Method) {
    try {
        val childObject = method.invoke(this) as RealmObject
        realm.deleteInCascade(childObject.javaClass)
    } catch (ex: Exception) {
        Log.e("RealmDelete", method.returnType.simpleName)
    }
}

private fun RealmModel.removeMethodAsRealmObject(realm: Realm, method: Method) {
    try {
        val childObject = method.invoke(this) as RealmObject
        realm.deleteInCascadeRealmModel(childObject.javaClass)
    } catch (ex: Exception) {
        Log.e("RealmDelete", method.returnType.simpleName)
    }
}

private fun RealmObject.removeMethodAsRealmClass(method: Method) {
    try {
        val childList = method.invoke(this) as RealmList<*>
        childList.forEach { child ->
            (child as? RealmObject)?.let { realmObject ->
                realm.deleteInCascade(realmObject.javaClass)
            }
        }
    } catch (ex: Exception) {
        Log.e("RealmDelete", method.returnType.simpleName + ex.message)
    }
}

private fun RealmModel.removeMethodAsRealmClass(realm: Realm, method: Method) {
    try {
        val childList = method.invoke(this) as RealmList<*>
        childList.forEach { child ->
            (child as? RealmObject)?.let { realmObject ->
                realm.deleteInCascadeRealmModel(realmObject.javaClass)
            }
        }
    } catch (ex: Exception) {
        Log.e("RealmDelete", method.returnType.simpleName + ex.message)
    }
}

fun Method.isNotGetter(): Boolean = !name.startsWith("get") || parameterTypes.isNotEmpty()

fun Method.isPrimitive(): Boolean = returnType.isPrimitive

fun Method.isRealmObject(): Boolean = RealmObject::class.java.isAssignableFrom(returnType)

fun Method.isRealmClass(): Boolean = RealmList::class.java.isAssignableFrom(returnType)