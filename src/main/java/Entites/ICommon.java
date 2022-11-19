/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Entites;

import java.util.ArrayList;

/**
 *
 * @author PC
 * @param <T>
 */
public interface ICommon<T> {
    ArrayList<T> getAll();
    T getOne(int id);
    boolean insert(T obj);
    boolean update(T obj);
    boolean delete(int id);
}
