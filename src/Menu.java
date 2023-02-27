import java.util.LinkedList;
import java.util.List;

public class Menu {
    private List<String> menuOptions = new LinkedList<>();
    private String header;

    public Menu(String menuHeader) {
        this.header = menuHeader;
    }

    public void addMenuOption(String option) {
        menuOptions.add(option);
    }

    public void changeTitle(String title) {
        this.header = title;
    }

    public void display() {
        System.out.println(header);

        for(String s : menuOptions)
            System.out.println(s);
    }

    public int getNumberOfOptions() {
        return menuOptions.size();
    }
}
