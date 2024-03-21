package dev.torbjornseth.cardgame;

import java.util.stream.Stream;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * The main class of the card game application.
 * The application creates a deck of cards,
 * deals a hand and checks for various combinations of cards.
 *
 * @version 1.0
 * @author Torbjørn Seth
 * @since 21.03.2024
 */
public class Main extends Application {
  private DeckOfCards deck;
  private Hand hand;
  private StackPane root;

  /**
   * The main method of the application. Initializes the JavaFX application,
   * the different components and starts the application.
   *
   * @param primaryStage the primary stage of the application
   *                     where the different components are added.
   * @since 1.0
   */
  @Override
  public void start(Stage primaryStage) {
    deck = new DeckOfCards();
    hand = new Hand(deck.dealHand(5));

    root = new StackPane();

    root.getChildren().add(new FlowPane());
    root.getChildren().add(new VBox());
    root.getChildren().add(new VBox());

    initButtons();
    updateCardBox();
    updateInfo();

    Scene scene = new Scene(root, 800, 500);

    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.setTitle("Card Game");
    primaryStage.show();
  }

  /**
   * The JavaFX entry, launches the application by running {@code mvn javafx:run}.
   *
   * @param args the command line arguments
   * @since 1.0
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Stops the application.
   *
   * @since 1.0
   */
  @Override
  public void stop() {
    System.exit(0);
  }

  /**
   * Updates the information about the hand of cards.
   * The information is displayed in a VBox and added to the root.
   * The info is displayed vertically in the order: sum, hearts, flush, queen of spades.
   *
   * @since 1.0
   */
  public void updateInfo() {


    // Removing the previous info box, if it exists
    // This is hard coded to remove the info box, as it is the third element in the root
    if (root.getChildren().size() > 2) {    // Size  (1, 2)
      root.getChildren().remove(2);  // Index (0, 1, 2)
    }

    // Adding the information to the info box
    Label sumField = new Label("Sum: " + hand.getSum());
    Label hearts = new Label("Hearts: " + hand.getSuits('H'));
    Label flush = new Label("Flush: " + hand.checkFlush(5));
    Label queenOfSpades = new Label("Queen of Spades: " + hand.containsCard('S', 12));

    Stream<Label> labelStream = Stream.of(sumField, hearts, flush, queenOfSpades);
    labelStream.forEach(label -> {
      label.setPadding(new Insets(10, 10, 10, 10));
      label.setStyle("-fx-font-size: 20");
      label.setMinWidth(200);
      label.setAlignment(javafx.geometry.Pos.CENTER);
    });

    VBox infoBox = new VBox();
    infoBox.setPadding(new Insets(275, 25, 25, 500));
    infoBox.getChildren().addAll(sumField, hearts, flush, queenOfSpades);

    root.getChildren().add(2, infoBox);
  }

  /**
   * Updates the card box with the cards in the hand.
   * The cards are displayed in a FlowPane and added to the root.
   * The cards are displayed horizontally.
   *
   * @since 1.0
   */
  public void updateCardBox() {
    // Styling the individual cards
    int imageWidth = 150;
    int imageHeight = 200;

    FlowPane cardBox = new FlowPane();
    cardBox.setPadding(new Insets(25, 25, 25, 25));

    // Iterating through the cards in the hand and adding them to the card box
    for (PlayingCard card : hand.getCards()) {
      Image image = new Image("file:src/main/resources/images/" + card.getAsString() + ".png");
      ImageView imageView = new ImageView(image);
      imageView.setFitWidth(imageWidth);
      imageView.setFitHeight(imageHeight);
      cardBox.getChildren().add(imageView);
    }
    root.getChildren().set(0, cardBox);
  }

  /**
   * Initializes the buttons of the application.
   * The buttons have a {@code Deal} and a {@code Check} action.
   *
   * @since 1.0
   */
  public void initButtons() {
    // Styling the buttons
    Border buttonBorder = new Border(new BorderStroke(
            Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT
    ));
    Background buttonBackground = new Background(
        new javafx.scene.layout.BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)
    );
    int minWidth = 100;
    int minHeight = 50;

    Button buttonDeal = new Button("Deal hand");
    Button buttonCheck = new Button("Check hand");

    // Apply the same style to both buttons
    Stream<Button> buttonStream = Stream.of(buttonDeal, buttonCheck);
    buttonStream.forEach(button -> {
      button.setBorder(buttonBorder);
      button.setBackground(buttonBackground);
      button.setMinWidth(minWidth);
      button.setMinHeight(minHeight);
    });

    VBox buttonBox = new VBox();
    buttonBox.setSpacing(10);
    buttonBox.setPadding(new Insets(300, 10, 10, 25));
    buttonBox.getChildren().addAll(buttonDeal, buttonCheck);

    buttonDeal.setOnAction(e -> {
      // Updating the hand and the card box
      PlayingCard[] cards = deck.dealHand(5);
      hand = new Hand(cards);
      updateCardBox();
    });

    // Updating the information about the hand
    buttonCheck.setOnAction(e -> updateInfo());

    root.getChildren().add(buttonBox);
  }
}
