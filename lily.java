// Interface que o sistema espera
abstract class NotificationService {
    abstract void send(String recipient, String message);
}

// Serviço de envio de e-mail com interface existente
class EmailService {
    void sendEmail(String toAddress, String content) {
        System.out.println("Enviando e-mail para " + toAddress + ": " + content);
    }
}

// Serviço de envio de SMS com interface diferente
class SMSService {
    void sendSms(String phoneNumber, String textMessage) {
        System.out.println("Enviando SMS para " + phoneNumber + ": " + textMessage);
    }
}

// Adapter para integrar o SMSService com a interface NotificationService
class SMSAdapter extends NotificationService {
    private SMSService smsService;

    public SMSAdapter(SMSService smsService) {
        this.smsService = smsService;
    }

    @Override
    void send(String recipient, String message) {
        smsService.sendSms(recipient, message);
    }
}

// Sistema de notificação que usa a interface NotificationService
class NotificationSystem {
    private NotificationService service;

    public NotificationSystem(NotificationService service) {
        this.service = service;
    }

    void notify(String recipient, String message) {
        service.send(recipient, message);
    }
}

// Demonstração do uso do Adapter
public class Main {
    public static void main(String[] args) {
        // Usando o EmailService diretamente
        EmailService emailService = new EmailService();
        NotificationService emailNotificationService = new NotificationService() {
            @Override
            void send(String recipient, String message) {
                emailService.sendEmail(recipient, message);
            }
        };
        NotificationSystem emailNotificationSystem = new NotificationSystem(emailNotificationService);
        emailNotificationSystem.notify("cris.dover@example.com", "Olá Cris!");

        // Usando o SMSService com o Adapter
        SMSService smsService = new SMSService();
        SMSAdapter smsAdapter = new SMSAdapter(smsService);
        NotificationSystem smsNotificationSystem = new NotificationSystem(smsAdapter);
        smsNotificationSystem.notify("1234567890", "Olá, este é um SMS!");
    }
}
