package de.linusschmidt.hpagi.environment;

import de.linusschmidt.hpagi.translation.Translator;
import de.linusschmidt.hpagi.utilities.FileUtil;
import de.linusschmidt.hpagi.utilities.Printer;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Environment extends JPanel {

    private Printer printer;
    private FileUtil fileUtil;
    private Dimension dimension;
    private Translator translator;

    private Entity npc;

    private List<Wall> walls;

    public Environment(Translator translator) {
        this.translator = translator;

        this.printer = new Printer();
        this.fileUtil = new FileUtil();
        this.dimension = new Dimension(500, 500);

        this.walls = new ArrayList<>();

        this.buildNPC();
        this.load();
        this.draw();
    }

    private void buildNPC() {
        int x = (int) Math.round(Math.random() * this.dimension.getWidth());
        int y = (int) Math.round(Math.random() * this.dimension.getHeight());
        this.npc = new Entity(x, y);
    }

    private void draw() {
        JFrame frame = new JFrame();
        frame.add(this);
        frame.setSize(this.getPreferredSize());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        for(Wall wall : this.walls) {
            if(wall.isVisible()) {
                g.fillRect(wall.getX(), wall.getY(), 10, 10);
            }
        }
        g.setColor(Color.GREEN);
        g.fillRect(this.npc.getX(), this.npc.getY(), 10, 10);
    }

    @Override
    public Dimension getPreferredSize() {
        return this.dimension;
    }

    private void load() {
        this.printer.printConsole("Loading environment walls...");
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(this.fileUtil.createFileInFolder("environment/wall", "wall.states")));
            String line;
            while((line = bufferedReader.readLine()) != null) {
                Wall wall = Wall.loadWall(line);
                this.walls.add(wall);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.printer.printConsole("done. Load!");
    }

    /*
    private void save() {
        this.printer.printConsole("Saving environment walls...");
        BufferedWriter bufferedWriter;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(this.fileUtil.createFileInFolder("environment/wall", "wall.state")));
            for(Wall wall : this.walls) {
                bufferedWriter.write(wall.toString());
                bufferedWriter.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.printer.printConsole("done. Save!");
    }
    */
}