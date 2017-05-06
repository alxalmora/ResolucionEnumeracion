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
public class Funciones {

    public static ArrayList<Nodo> createNodeList(String guiInput) {
        ArrayList<Nodo> list = new ArrayList<>();
        Nodo temp;
        //Scanner scan = new Scanner(new File("input.txt"));
        Scanner scan = new Scanner(guiInput);
        String[] line;
        while (scan.hasNextLine()) {
            line = scan.nextLine().split(",");
            for (String word : line) {
                temp = new Nodo(word);
                if (!list.contains(temp)) {
                    list.add(temp);
                } else {
                    temp = list.get(list.indexOf(temp));
                }
                for (String adj : line) {
                    if (!temp.getNombre().equals(adj)) {
                        temp.addAdyacente(adj);
                    }
                }
            }
        }
        Collections.sort(list);
        return list;
    }

}
