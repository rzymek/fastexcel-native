package org.rzymek;

import org.dhatim.fastexcel.reader.Cell;
import org.dhatim.fastexcel.reader.ReadableWorkbook;

import java.io.File;

import static java.util.stream.Collectors.joining;

public class Main {
    public static void main(String[] args) throws Exception {
        File inputFile = new File(args[0]);
        try (ReadableWorkbook wb = new ReadableWorkbook(inputFile)) {
            wb.getFirstSheet().openStream().forEach(row -> {
                String str = row.stream()
                        .map(Cell::getRawValue)
                        .collect(joining(","));
                System.out.println(str);
            });
        }
    }
}
