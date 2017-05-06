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

import java.util.ArrayList;

/**
 *
 * @author alxalmora
 */
public class Colores implements Comparable{
    ArrayList<String> list;
    int restriccion;
    public Colores(int restriccion){
        this.restriccion = restriccion;
        list = new ArrayList<>();
    }
    public void addColor(String color){
        list.add(color);
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Colores other = (Colores) obj;
        if (this.restriccion != other.restriccion) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Object o) {
       if (o == null) {
            return -1;
        }
        if (getClass() != o.getClass()) {
            return -1;
        }else{
            Colores aux = (Colores)o;
            return Integer.compare(restriccion, aux.restriccion);
        }
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.restriccion).append(":\t");
        for(int i=0;i<list.size();i++){
            sb.append(list.get(i)).append("\t");
        }
        return sb.toString();
    }
    public int size(){
        return list.size();
    }
}
