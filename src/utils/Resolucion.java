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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alxalmora
 */
public class Resolucion {

    private ArrayList<Nodo> nodos;
    private int closed;
    private ArrayList<Colores> colors;
    private int maxSize;

    public String[] getHeaders(){
        String[] resp = new String[nodos.size()];
        for(int i=0;i<resp.length;i++){
            resp[i] = nodos.get(i).getNombre();
        }
        return resp;
    }
    public void setMaxSize(int maxSize){
        this.maxSize = maxSize;
    }
    public Resolucion(String guiInput) {
        colors = new ArrayList<>();
        nodos = Funciones.createNodeList(guiInput);
        //this.getRestrictions();
        this.closed = 0;
        this.maxSize = 0;
    }
    public Resolucion(int maxSize, String guiInput){
        this(guiInput);
        this.maxSize = maxSize;
    }

    private String headerString() {
        String resp = "";
        for (Nodo node : nodos) {
            resp += node.getNombre() + "\t";
        }
        resp += "Restriccion";
        return resp;
    }

    private String numberString() {
        String resp = "";
        for (Nodo node : nodos) {
            if (node.isClosed()) {
                resp += "-\t";
            } else {
                resp += node.getSize() + "\t";
            }
        }
        return resp;
    }

    private Nodo getMax() {
        Nodo max = new Nodo("max");
        for (Nodo node : nodos) {
            if (!node.isClosed()) {
                if(node.getSize()==0){
                    return node;
                }
                if (node.getSize() >= max.getSize()) {
                    max = node;
                }
            }
        }
        return max;
    }

    public void getRestrictions() {
        try {
            Scanner scan = new Scanner(new File("restrictions.txt"));
            String[] line;
            Nodo restricted;
            while (scan.hasNextLine()) {
                line = scan.nextLine().split(",");
                restricted = nodos.get(nodos.indexOf(new Nodo(line[0])));
                for (int i = 1; i <= Integer.parseInt(line[1]); i++) {
                    restricted.setRestricciones(i);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Resolucion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean addInitialRestrictions(String nombre, int num){
        Nodo restricted = new Nodo(nombre);
        if(nodos.indexOf(restricted)==-1){
            return false;
        }
        if(nodos.get(nodos.indexOf(restricted)).getRestricciones()!=0){
            return false;
        }
        for(int i=1;i<num;i++){
            restricted = nodos.get(nodos.indexOf(restricted));
            restricted.setRestricciones(i);
        }
        return true;
    }
    public ArrayList<String> creaColores() {
        ArrayList<String> resp = new ArrayList<>();
        resp.add(headerString());
        Nodo workingNode;
        int restriccion;
        while (this.closed < this.nodos.size()) {
            workingNode = this.getMax();
            restriccion = workingNode.getMinRestriction();
            if(maxSize != 0){
                    while(colors.indexOf(new Colores(restriccion))!= -1 && colors.get(colors.indexOf(new Colores(restriccion))).size()>=maxSize){
                        //System.out.println(colors.get(colors.indexOf(restriccion)).size());
                        workingNode.setRestricciones(restriccion);
                        restriccion = workingNode.getMinRestriction();
                    }
                
            }
            resp.add(numberString()+restriccion);
            for (Nodo node : nodos) {
                if (node.equals(workingNode)) {
                    node.close();
                } else {
                    if(workingNode.contains(node.getNombre())){
                        node.removeAdyacente(workingNode.getNombre());
                        node.setRestricciones(restriccion);
                    }
                }
            }
            Colores colorAux = new Colores(restriccion);
            if (colors.contains(colorAux)) {
                colorAux = colors.get(colors.indexOf(colorAux));
            } else {
                colors.add(colorAux);
            }
            colorAux.addColor(workingNode.getNombre());
            this.closed++;
        }
        return resp;
    }

    public String imprimeColores(){
        StringBuilder sb = new StringBuilder();
        Collections.sort(colors);
        Colores[] impCol = new Colores[colors.size()];
        impCol = colors.toArray(impCol);
        for(Colores col:impCol){
            sb.append(col.toString()).append("\n");
        }
        return sb.toString();
    }
}
