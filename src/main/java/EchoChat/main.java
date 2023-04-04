package EchoChat;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class main {
    public static void main(String[] args) {


        String token = "6074661394:AAHeLWxsikrWkeGq_xWlpRFVgLlglLveTDE";
        String username = "hamdenJavaBot";


        TelegramBotsApi telegramBotsApi = null;
        try {
            telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new HamdenBot(username,token));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    }

