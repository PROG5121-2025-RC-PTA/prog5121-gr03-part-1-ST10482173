import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StoreAndDisplay {
    private String messageID;
    private String recipient;
    private String messageText;
    private String messageHash;
    private static final List<String> messageList = new ArrayList<>();
    private static final List<String> disregardedMessages = new ArrayList<>();
    private static final List<String> messageHashes = new ArrayList<>();
    private static final List<String> messageIDs = new ArrayList<>();

    public StoreAndDisplay( String messageID, String recipient, String messageText){
        this.messageID = messageID;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
        messageHashes.add(this.messageHash);
        messageIDs.add(messageID);
    }
    //Method for generating message Hash
    private String createMessageHash() {
        return String.valueOf(new Random().nextInt(100000));
    }
    //Method to validate message and recipient
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }
    public boolean checkRecipientCell() {
        return recipient.length() <= 10 && recipient.startsWith("+");
    }
    //Method for sending Messages
    public void sentMessage() {
        if (checkMessageID() && checkRecipientCell()) {
            messageList.add("ID: " + messageID + ", Hash: " + messageHash + ", Recipient: " + recipient + ", Message: " + messageText);
            JOptionPane.showMessageDialog(null, "Message Sent: \nID: " + messageID +
                    "\nHash: " + messageHash +
                    "\nRecipient: " + recipient +
                    "\nMessage: " + messageText);
        } else {
            JOptionPane.showMessageDialog(null, "INVALID MESSAGE ID OR RECIPIENT CELL NUMBER");
            disregardedMessages.add("Invalid Message ID or Recipient Cell for ID: " + messageID);
        }
    }
    //Method to display messages
    public static String printMessages() {
        StringBuilder printedMessages = new StringBuilder();
        for (String msg : messageList) {
            printedMessages.append(msg).append("\n");
        }
        return printedMessages.toString();
    }
    //Method to return total messages
    public static int returnTotalMessages() {
        return messageList.size();
    }
    //Method to get the longest message
    public static String getLongestMessage() {
        String longestMessage = "";
        for (String msg : messageList) {
            String[] parts = msg.split(", Message: ");
            String messageText = parts[1];
            if (messageText.length() > longestMessage.length()) {
                longestMessage = messageText;
            }
        }
        return longestMessage;
    }
    //Method to search messages by ID
    public static String searchByMessageID(String searchID) {
        for (String msg : messageList) {
            if (msg.contains("ID: " + searchID)) {
                return msg;
            }
        }
        return "Message ID not found.";
    }
    //Method to search all messages for a recipient
    public static String searchByRecipient(String recipient) {
        StringBuilder results = new StringBuilder();
        for (String msg : messageList) {
            if (msg.contains("Recipient: " + recipient)) {
                results.append(msg).append("\n");
            }
        }
        return results.length() > 0 ? results.toString() : "No messages found for this recipient.";
    }
    //Method to delete message by Hash
    public static String deleteMessageByHash(String hash) {
        for (int i = 0; i < messageList.size(); i++) {
            if (messageList.get(i).contains("Hash: " + hash)) {
                messageList.remove(i);
                return "Message with hash " + hash + " deleted.";
            }
        }
        return "Message not found.";
    }
    //Method to display report
    public static String reportAllMessages() {
        return printMessages(); //Returns all messages
    }
}
class Chats {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat");
        boolean running = true;
        while (running) {
            String menu = """
                    1. Send a Message
                    2. Show recently sent messages
                    3. Find the longest message
                    4. Find message by ID
                    5. Find messages by recipient
                    6. Delete message by hash
                    7. Display report of all messages
                    8. Quit""";
            String choice = JOptionPane.showInputDialog(menu);

            switch (choice) {
                case "1":
                    sendMessages();
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, "Sent Messages: \n" + StoreAndDisplay.printMessages());
                    break;
                case "3":
                    JOptionPane.showMessageDialog(null, "Longest Message: " + StoreAndDisplay.getLongestMessage());
                    break;
                case "4":
                    String searchID = JOptionPane.showInputDialog("Enter Message ID to search:");
                    JOptionPane.showMessageDialog(null, StoreAndDisplay.searchByMessageID(searchID));
                    break;
                case "5":
                    String recipient = JOptionPane.showInputDialog("Enter recipient's cell number:");
                    JOptionPane.showMessageDialog(null, StoreAndDisplay.searchByRecipient(recipient));
                    break;
                case "6":
                    String deleteHash = JOptionPane.showInputDialog("Enter the hash of the message to delete:");
                    JOptionPane.showMessageDialog(null, StoreAndDisplay.deleteMessageByHash(deleteHash));
                    break;
                case "7":
                    JOptionPane.showMessageDialog(null, "All Messages:\n" + StoreAndDisplay.reportAllMessages());
                    break;
                case "8":
                    running = false;
                    JOptionPane.showMessageDialog(null, "Goodbye!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
            }
        }
    }
    private static void sendMessages() {
        for (int i = 0; i < 2; i++) {
            String messageID = JOptionPane.showInputDialog("Enter Message ID (max 10 characters):");
            String recipient = JOptionPane.showInputDialog("Enter recipient cell number (max 10 characters, must start with +):");
            String messageText = JOptionPane.showInputDialog("Enter your message (max 250 characters):");
            if (messageText.length() > 250) {
                JOptionPane.showMessageDialog(null, "Message too long. Please enter under 250 characters.");
                i--; // Retry
                continue;
            }
            StoreAndDisplay storeAndDisplay = new StoreAndDisplay(messageID, recipient, messageText);
            storeAndDisplay.sentMessage();
        }
    }
}

