/*
 * The MIT License
 *
 * Copyright 2017 alxalmora.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package utils;

import java.util.HashSet;
import java.util.Objects;

/**
 *
 * @author alxalmora
 */
public class Nodo implements Comparable {
    private final HashSet<String> adyacentes;
    private final String nombre;
    private int restricciones;
    private boolean isClosed;
    public Nodo(String nombre){
        this.nombre = nombre;
        this.isClosed = false;
        this.adyacentes = new HashSet<>();
        this.restricciones = 0;
    }
    public boolean addAdyacente(String adj){
        return this.adyacentes.add(adj);
    }
    public boolean contains(String adj){
        return this.adyacentes.contains(adj);
    }
    public boolean removeAdyacente(String adj){
        return this.adyacentes.remove(adj);
    }
    public int getSize(){
        return this.adyacentes.size();
    }
    public void setRestricciones(int n){
        if((this.restricciones&(int)Math.pow(2, n-1))==0){
        this.restricciones += Math.pow(2, n-1);    
        }
    }
    public int getMinRestriction(){
        int i = 1;
        while(((int)Math.pow(2, i-1)& this.restricciones)!=0){
            i++;
        }
        return i;
    }
   
    public String getNombre() {
        return nombre;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Nodo other = (Nodo) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.nombre).append(":\t");
        String[] aux = new String[this.adyacentes.size()];
        aux = this.adyacentes.toArray(aux);
        for (String adj : aux) {
            sb.append(adj).append("\t");
        }
        return sb.toString();
        
    }
    @Override
    public int compareTo(Object o) {
        if(this.equals(o)){
            return 0;
        }
        if(getClass() != o.getClass()){
            return -1;
        }else{
            Nodo aux = (Nodo) o;
            return this.nombre.compareTo(aux.nombre);
        }
        
    }
    public boolean isClosed(){
        return this.isClosed;
    }
    public void close(){
        this.isClosed = true;
    }

    public int getRestricciones() {
        return restricciones;
    }
    
}
