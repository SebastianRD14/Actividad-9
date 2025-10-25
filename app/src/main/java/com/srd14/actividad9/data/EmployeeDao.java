package com.srd14.actividad9.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.srd14.actividad9.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    private static final String TAG = "EmployeeDao";
    private final DBHelper helper;

    public EmployeeDao(Context context) {
        helper = new DBHelper(context);
    }

    // Insertar nuevo empleado
    public long insert(Employee employee) {
        SQLiteDatabase db = helper.getWritableDatabase();
        long newRowId = -1;

        try {
            ContentValues values = new ContentValues();
            values.put(DBContract.EmployeeEntry.COL_NAME, employee.getName());
            values.put(DBContract.EmployeeEntry.COL_POSITION, employee.getPosition());
            values.put(DBContract.EmployeeEntry.COL_SALARY, employee.getSalary());

            newRowId = db.insert(DBContract.EmployeeEntry.TABLE_NAME, null, values);
            Log.d(TAG, "Empleado insertado con ID: " + newRowId);
        } catch (SQLException e) {
            Log.e(TAG, "Error al insertar empleado: " + e.getMessage());
        } finally {
            db.close();
        }

        return newRowId;
    }

    // Actualizar empleado existente
    public int update(Employee employee) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int rowsAffected = 0;

        try {
            ContentValues values = new ContentValues();
            values.put(DBContract.EmployeeEntry.COL_NAME, employee.getName());
            values.put(DBContract.EmployeeEntry.COL_POSITION, employee.getPosition());
            values.put(DBContract.EmployeeEntry.COL_SALARY, employee.getSalary());

            String whereClause = DBContract.EmployeeEntry._ID + " = ?";
            String[] whereArgs = { String.valueOf(employee.getId()) };

            rowsAffected = db.update(DBContract.EmployeeEntry.TABLE_NAME, values, whereClause, whereArgs);
            Log.d(TAG, "Empleado actualizado. Filas afectadas: " + rowsAffected);
        } catch (SQLException e) {
            Log.e(TAG, "Error al actualizar empleado: " + e.getMessage());
        } finally {
            db.close();
        }

        return rowsAffected;
    }

    // Eliminar empleado por ID
    public int delete(long id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int rowsDeleted = 0;

        try {
            String whereClause = DBContract.EmployeeEntry._ID + " = ?";
            String[] whereArgs = { String.valueOf(id) };

            rowsDeleted = db.delete(DBContract.EmployeeEntry.TABLE_NAME, whereClause, whereArgs);
            Log.d(TAG, "Empleado eliminado. Filas eliminadas: " + rowsDeleted);
        } catch (SQLException e) {
            Log.e(TAG, "Error al eliminar empleado: " + e.getMessage());
        } finally {
            db.close();
        }

        return rowsDeleted;
    }

    // Obtener todos los empleados
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();

        String[] projection = {
                DBContract.EmployeeEntry._ID,
                DBContract.EmployeeEntry.COL_NAME,
                DBContract.EmployeeEntry.COL_POSITION,
                DBContract.EmployeeEntry.COL_SALARY
        };

        try (Cursor cursor = db.query(
                DBContract.EmployeeEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                DBContract.EmployeeEntry.COL_NAME + " ASC"
        )) {
            while (cursor.moveToNext()) {
                Employee employee = new Employee();
                employee.setId(cursor.getLong(cursor.getColumnIndexOrThrow(DBContract.EmployeeEntry._ID)));
                employee.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.EmployeeEntry.COL_NAME)));
                employee.setPosition(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.EmployeeEntry.COL_POSITION)));
                employee.setSalary(cursor.getDouble(cursor.getColumnIndexOrThrow(DBContract.EmployeeEntry.COL_SALARY)));

                employees.add(employee);
            }
            Log.d(TAG, "Se obtuvieron " + employees.size() + " empleados");
        } catch (SQLException e) {
            Log.e(TAG, "Error al obtener empleados: " + e.getMessage());
        } finally {
            db.close();
        }

        return employees;
    }

    // Obtener empleado por ID
    public Employee getById(long id) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Employee employee = null;

        String[] projection = {
                DBContract.EmployeeEntry._ID,
                DBContract.EmployeeEntry.COL_NAME,
                DBContract.EmployeeEntry.COL_POSITION,
                DBContract.EmployeeEntry.COL_SALARY
        };

        String selection = DBContract.EmployeeEntry._ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };

        try (Cursor cursor = db.query(
                DBContract.EmployeeEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        )) {
            if (cursor.moveToFirst()) {
                employee = new Employee();
                employee.setId(cursor.getLong(cursor.getColumnIndexOrThrow(DBContract.EmployeeEntry._ID)));
                employee.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.EmployeeEntry.COL_NAME)));
                employee.setPosition(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.EmployeeEntry.COL_POSITION)));
                employee.setSalary(cursor.getDouble(cursor.getColumnIndexOrThrow(DBContract.EmployeeEntry.COL_SALARY)));
            }
        } catch (SQLException e) {
            Log.e(TAG, "Error al obtener empleado por ID: " + e.getMessage());
        } finally {
            db.close();
        }

        return employee;
    }
}