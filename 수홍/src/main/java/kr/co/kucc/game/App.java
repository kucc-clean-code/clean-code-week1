/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package kr.co.kucc.game;

import kr.co.kucc.cat.Cat;
import kr.co.kucc.cat.CatRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private CommandFactory commandFactory;
    private CatRepository catRepository;

    public App(CommandFactory commandFactory,
               CatRepository catRepository) {
        this.commandFactory = commandFactory;
        this.catRepository = catRepository;
    }

    private List<Cat> cats = new ArrayList<>();
    private boolean isRunning = true;

    public void run() {
        Scanner scanner = new Scanner(System.in);
        this.cats = new ArrayList<>(catRepository.readAll());

        while (this.isRunning) {
            Command command = getCommand(scanner);
            command.run(this);
        }
        scanner.close();
    }

    private Command getCommand(Scanner scanner) {
        try {
            return commandFactory.getCommand(scanner.nextLine());
        } catch (CannotFindCommandException e) {
            return new EmptyCommand();
        }
    }


    public List<Cat> getCats() {
        return cats;
    }

    public void stop() {
        this.isRunning = false;
    }

    public void addCat(Cat cat) {
        this.cats.add(cat);
    }
}
