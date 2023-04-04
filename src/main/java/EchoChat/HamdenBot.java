package EchoChat;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class HamdenBot extends TelegramLongPollingBot {

    public final String botName;
    public final String botToken;
    public static String var = "";

    public HamdenBot(String botName, String botToken) {
        this.botName = botName;
        this.botToken = botToken;
    }

    @Override
    public String getBotUsername() {
        return "hamdenJavaBot";
    }

    @Override
    public String getBotToken() {
        return "6074661394:AAHeLWxsikrWkeGq_xWlpRFVgLlglLveTDE";
    }




    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasCallbackQuery()){
            var = handleCallback(update.getCallbackQuery());
        }else if (update.hasMessage()){
            handleMessage(update.getMessage());
            if (!var.isEmpty()){
                calculations(update.getMessage(),var);
            }
        }


    }


    @SneakyThrows
    private String handleCallback(CallbackQuery callbackQuery) {
        return callbackQuery.getData();
    }


    @SneakyThrows
    private void handleMessage(Message message) {
    //handle command
    if(message.hasText() && message.hasEntities()){
        Optional<MessageEntity> commandEntity =
                message
                        .getEntities()
                        .stream()
                        .filter(e -> "bot_command".equals(e.getType())).findFirst();
        if(commandEntity.isPresent()){
            String command = message.getText().substring(commandEntity.get().getOffset(), commandEntity.get().getLength());

            switch (command){
                case "/begin":
                List<InlineKeyboardButton> buttons = new ArrayList<>();
                buttons.add(InlineKeyboardButton.builder().text("1").callbackData("1").build());
                buttons.add(InlineKeyboardButton.builder().text("2").callbackData("2").build());
                buttons.add(InlineKeyboardButton.builder().text("3").callbackData("3").build());
                buttons.add(InlineKeyboardButton.builder().text("4").callbackData("4").build());
                buttons.add(InlineKeyboardButton.builder().text("5").callbackData("5").build());
                buttons.add(InlineKeyboardButton.builder().text("6").callbackData("6").build());
                buttons.add(InlineKeyboardButton.builder().text("7").callbackData("7").build());
                    execute(SendMessage
                            .builder()
                            .text("Выберите вариант.")
                            .chatId(message.getChatId().toString())
                            .replyMarkup(InlineKeyboardMarkup.builder().keyboardRow(buttons).build())
                            .build());
                    execute(SendMessage.builder().chatId(message.getChatId().toString()).text("Введите значения a,b,c,d,x,y,n,w,a1,a2 через пробел ").build());
                    break;
                case "/formulas":
                    execute(SendPhoto.builder()
                            .chatId(message.getChatId().toString())
                            .photo(new InputFile(new File("C:\\Users\\Admin\\Downloads\\Formulas.png")))
                            .caption("Это список формул.")
                            .build());
                    break;
            }
        }
    }

    }

    @SneakyThrows
    private void calculations(Message message, String var) {
        if (message.hasText()) {
            System.out.println(var);
            double res;
            String messageText = message.getText();
            if (!messageText.matches("[0-9 ]+") || (messageText.length() <= 18 )){
                execute(SendMessage.builder().chatId(message.getChatId().toString()).text("Введите только 10 чисел строго через пробел!").build());
            }else {
            String[] values = messageText.split(" ");
            double a = Double.parseDouble(values[0]);
            double b = Double.parseDouble(values[1]);
            double c = Double.parseDouble(values[2]);
            double d = Double.parseDouble(values[3]);
            double x = Double.parseDouble(values[4]);
            double y = Double.parseDouble(values[5]);
            double n = Double.parseDouble(values[6]);
            double w = Double.parseDouble(values[7]);
            double a1 = Double.parseDouble(values[8]);
            double a2 = Double.parseDouble(values[9]);

            switch (var) {
                case "1" -> {
                    res = ((5 * Math.pow(a, n * x)) / (b + c)) - (Math.sqrt(Math.abs(Math.cos(Math.pow(x, 3)))));
                    execute(SendMessage.builder().chatId(message.getChatId().toString()).text("Ваш ответ " + res).build());
                }
                case "2" -> {
                    res = (Math.abs(x - y) / Math.pow((1 + 2 * x), a)) - Math.exp(Math.sqrt(1 + w));
                    execute(SendMessage.builder().chatId(message.getChatId().toString()).text("Ваш ответ " + res).build());
                }
                case "3" -> {
                    res = Math.sqrt(a + a1 * x + a2 * Math.pow(Math.sqrt(Math.abs(Math.sin(x))), 0.33));
                    execute(SendMessage.builder().chatId(message.getChatId().toString()).text("Ваш ответ " + res).build());
                }
                case "4" -> {
                    res = Math.log(Math.abs(Math.pow(a, 7))) + Math.atan(x * x) + (Math.PI / (Math.sqrt(Math.abs(a + x))));
                    execute(SendMessage.builder().chatId(message.getChatId().toString()).text("Ваш ответ " + res).build());
                }
                case "5" -> {
                    res = Math.pow(Math.sqrt(Math.pow(a + b, 2) / c + d) + Math.exp(Math.sqrt(x + 1)), 1.0 / 5.0);
                    execute(SendMessage.builder().chatId(message.getChatId().toString()).text("Ваш ответ " + res).build());
                }
                case "6" -> {
                    res = Math.exp((2.0 * Math.sin(4.0 * x) + Math.pow(Math.cos(Math.pow(x, 2.0)), 2.0))) / (3.0 * x);
                    execute(SendMessage.builder().chatId(message.getChatId().toString()).text("Ваш ответ " + res).build());
                }
                case "7" -> {
                    res = 0.25 * (((1.0 + Math.pow(x, 2.0)) / (1.0 - x)) + 0.5 * Math.tan(x));
                    execute(SendMessage.builder().chatId(message.getChatId().toString()).text("Ваш ответ " + res).build());
                }
            }
            }
        }
    }













}
