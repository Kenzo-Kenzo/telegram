package org.example;

import com.fasterxml.jackson.core.JsonParseException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class MyBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            Long chatId = message.getChatId();

            if (message.getText() != null && message.getText().equals("/start")) {
                sendTextMessage(chatId, "Ismini tez kirit xaxaxaxa");
            } else if (message.getText() != null && !message.getText().equals("/start")) {
                sendContactRequest(chatId);
            } else if (message.hasContact()) {
                sendLocationRequest(chatId);
            }
        } else if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            Long chatId = update.getCallbackQuery().getMessage().getChatId();

            if ("accept_policy".equals(callbackData)) {
                sendTextMessage(chatId, "Rahmat!");
            }
        }
    }

    private void sendTextMessage(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendContactRequest(Long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Iltimos, telefon raqamingizni yuboring:");

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);

        KeyboardRow row = new KeyboardRow();
        KeyboardButton contactButton = new KeyboardButton("Kontaktni yuborish");
        contactButton.setRequestContact(true);
        row.add(contactButton);

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);

        message.setReplyMarkup(keyboardMarkup);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendLocationRequest(Long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("lokatsiyan tasha:");

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);

        KeyboardRow row = new KeyboardRow();
        KeyboardButton locationButton = new KeyboardButton("loketsiyeni tashavor");
        locationButton.setRequestLocation(true);
        row.add(locationButton);

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);

        message.setReplyMarkup(keyboardMarkup);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        JsonParseException update = null;
        Long chatId;
        chatId = update.getMessage().getChars();

        Message message = new Message();


        if (message.hasContact()) {
            System.out.println(message.getContact());
            return;
        }
        String text = message.getText();


        KeyboardButton button = new KeyboardButton();
        button.setText("Click Premium");

        KeyboardButton button3 = new KeyboardButton();
        button3.setText("\uD83D\uDECD Buyurtma berish");

        KeyboardButton button4 = new KeyboardButton();
        button4.setText("\uD83C\uDF89 Aksiya");

        KeyboardButton button5 = new KeyboardButton();
        button5.setText("\uD83C\uDFD8 Barcha filliallar");

        KeyboardButton button6 = new KeyboardButton();
        button6.setText("\uD83D\uDCC6 Mening buyurtmalarim");

        KeyboardButton button7 = new KeyboardButton();
        button7.setText("\uD83D\uDCDD Izoh qoldirish");

        KeyboardButton button8 = new KeyboardButton();
        button8.setText("\uD83D\uDCBC  Vakansiyalar");

        KeyboardButton button9 = new KeyboardButton();
        button9.setText("ℹ️\uFE0F Biz haqimizda");
        button9.setRequestLocation(true);
        KeyboardRow row1 = new KeyboardRow();
        row1.add(button3);

        KeyboardRow row2 = new KeyboardRow();
        row2.add(button4);
        row2.add(button5);

        KeyboardRow row3 = new KeyboardRow();
        row3.add(button6);
        row3.add(button7);

        KeyboardRow row4 = new KeyboardRow();
        row4.add(button8);
        row4.add(button9);


        List<KeyboardRow> rows = new ArrayList<>();
        rows.add(row1);
        rows.add(row2);
        rows.add(row3);
        rows.add(row4);

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setKeyboard(rows);

        SendMessage message1 = new SendMessage();
        message1.setChatId(chatId);
        message1.setText("Tugmalar");
        message1.setReplyMarkup(markup);


        try {
            execute(message1);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);


        }
    }

    @Override
    public String getBotToken() {
        return "7697574820:AAF6cwZ-mS6h9ggd9bcRHAG6uahnl2FJyZQ";
    }

    @Override
    public String getBotUsername() {
        return "https://t.me/Uxlela_bot";
    }
}
