# Interface que o sistema espera
class NotificationService:
    def send(self, recipient: str, message: str):
        raise NotImplementedError("Subclasses devem implementar este método.")

# Serviço de envio de e-mail com interface existente
class EmailService:
    def send_email(self, to_address: str, content: str):
        print(f"Enviando e-mail para {to_address}: {content}")

# Serviço de envio de SMS com interface diferente
class SMSService:
    def send_sms(self, phone_number: str, text_message: str):
        print(f"Enviando SMS para {phone_number}: {text_message}")

# Adapter para integrar o SMSService com a interface NotificationService
class SMSAdapter(NotificationService):
    def __init__(self, sms_service: SMSService):
        self.sms_service = sms_service

    def send(self, recipient: str, message: str):
        self.sms_service.send_sms(recipient, message)

# Sistema de notificação que usa a interface NotificationService
class NotificationSystem:
    def __init__(self, service: NotificationService):
        self.service = service

    def notify(self, recipient: str, message: str):
        self.service.send(recipient, message)

# Demonstração do uso do Adapter
def main():
    # Usando o EmailService diretamente
    email_service = EmailService()
    email_notification_service = EmailNotificationService(email_service)
    email_notification_system = NotificationSystem(email_notification_service)
    email_notification_system.notify("cris.dover@example.com", "Olá Cris!")

    # Usando o SMSService com o Adapter
    sms_service = SMSService()
        sms_adapter = SMSAdapter(sms_service)
    sms_notification_system = NotificationSystem(sms_adapter)
    sms_notification_system.notify("1234567890", "Olá, este é um SMS!")

# Classe para EmailNotificationService, que foi esquecida na versão anterior
class EmailNotificationService(NotificationService):
    def __init__(self, email_service: EmailService):
        self.email_service = email_service

    def send(self, recipient: str, message: str):
        self.email_service.send_email(recipient, message)

if __name__ == "__main__":
    main()
