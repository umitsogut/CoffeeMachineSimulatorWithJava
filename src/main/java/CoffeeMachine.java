int money = 550;
int water = 400;
int milk = 540;
int beans = 120;
int cups = 9;
int orders = 0;
boolean isClean = true;

Coffee espresso = new Coffee(250, 0, 16, 4);
Coffee latte = new Coffee(350, 75, 20, 7);
Coffee cappuccino = new Coffee(200, 100, 12, 6);

void main() {
    init();
    listenForAction();
}

void init() {
    IO.println("The coffee machine has:");
    IO.println(water + " ml of water");
    IO.println(milk + " ml of milk");
    IO.println(beans + " g of coffee beans");
    IO.println(cups + " disposable cups");
    IO.println("£" + money + " of money");
}

void listenForAction() {
    Scanner scanner = new Scanner(System.in);
    IO.println("\nWrite action (buy, fill, take, clean, remaining, exit): ");
    String action = scanner.nextLine();
    switch (action) {
        case "buy" -> buy();
        case "fill" -> fill();
        case "take" -> take();
        case "clean" -> clean();
        case "remaining" -> remaining();
        case "exit" -> exit();
    }
}

boolean checkStock(Coffee coffee) {
    if (water < coffee.water) {
        System.out.println("Sorry, not enough water!");
        return false;
    }
    if (milk < coffee.milk) {
        System.out.println("Sorry, not enough milk!");
        return false;
    }
    if (beans < coffee.beans) {
        System.out.println("Sorry, not enough coffee beans!");
        return false;
    }
    if (cups < 1) {
        System.out.println("Sorry, not enough disposable cups!");
        return false;
    }
    return true;
}

void buy() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
    int choice = scanner.nextInt();

    Coffee selected = null;

    if (choice == 1) selected = espresso;
    else if (choice == 2) selected = latte;
    else if (choice == 3) selected = cappuccino;

    if (selected != null && checkStock(selected)) {
        System.out.println("I have enough resources, making you a coffee!");

        water -= selected.water;
        milk -= selected.milk;
        beans -= selected.beans;
        cups--;
        money += selected.price;

        orders++;
    }
    listenForAction();
}

void fill() {
    Scanner scanner = new Scanner(System.in);
    IO.println("Write how many ml of water you want to add: ");
    int ml = scanner.nextInt();
    water += ml;
    IO.println("Write how many ml of milk you want to add: ");
    int mlk = scanner.nextInt();
    milk += mlk;
    IO.println("Write how many grams of coffee beans you want to add: ");
    int beans = scanner.nextInt();
    beans += beans;
    IO.println("Write how many disposable cups you want to add: ");
    int cups = scanner.nextInt();
    cups += cups;
    init();
    listenForAction();
}

void take() {
    IO.println("I gave you $" + money);
    money = 0;
    init();
    listenForAction();
}

void clean() {
    IO.println("I have been cleaned!");
    isClean = true;
    orders = 0;
    listenForAction();
}

void remaining() {
    init();
    listenForAction();
}

void exit() {
    System.exit(0);
}
