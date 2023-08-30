package com.example.androidapp.utility;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmOpearations
{

    /*public void addStudent(StudentRealm studentRealm)
    {
        try(Realm realm=Realm.getDefaultInstance())
        {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm _realm)
                {
                    _realm.insert(studentRealm);

                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }*/

    public List<StudentRealm> getStudentList()
    {
        List<StudentRealm> studentRealmList=null;
        try(Realm realm=Realm.getDefaultInstance())
        {
            RealmResults<StudentRealm>  _studentRealmList=realm.where(StudentRealm.class).findAll();
            if(_studentRealmList != null)
            {
                studentRealmList=realm.copyFromRealm(_studentRealmList);

            }
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
        return studentRealmList;
    }
}
