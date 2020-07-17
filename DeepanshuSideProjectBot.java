import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class DeepanshuSideProjectBot extends TelegramLongPollingBot {
	InputOutputHandler IOhandle=new InputOutputHandler(); 
    public void onUpdateReceived(Update update) {
    	
        String botInput=update.getMessage().getText();
        long chat_id = update.getMessage().getChatId();
        System.out.println("Last Question "+IOhandle.getLastQuestion());
        System.out.println(botInput);
        IOhandle.setBotInput(botInput);
        IOhandle.setBotId(chat_id);
        IOhandle.execute();
        	SendMessage message = new SendMessage() // Create a message object object
                    .setChatId(IOhandle.getBotId())
                    .setText(IOhandle.getBotOutput());
            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            IOhandle.setLastQuestion(IOhandle.getBotOutput());
    }

    public String getBotUsername() {
        return "DeepanshuSideProjectBot";
    }

    public String getBotToken() {
        return "1140703180:AAEyNfzouTwe_thQ8ZPQmczpw2oiPUa4Wcw";
    }
}
