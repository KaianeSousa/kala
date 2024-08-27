# Cenário
#Vamos supor que você tem uma aplicação que precisa lidar com uma interface de pagamento.
#A aplicação espera uma interface com métodos como process_payment(amount), mas você tem
#uma classe existente que só oferece uma interface com um método diferente, make_transaction(amount).

#Vamos criar um adaptador para converter a chamada de process_payment(amount) para make_transaction(amount).



# Interface esperada pela aplicação
class PaymentProcessor:
    def process_payment(self, amount):
        raise NotImplementedError("Subclass must implement this method")

# Classe com a interface existente
class OldPaymentService:
    def make_transaction(self, amount):
        print(f"Processing transaction of ${amount}")

# Adaptador que adapta OldPaymentService para PaymentProcessor
class PaymentAdapter(PaymentProcessor):
    def __init__(self, old_payment_service):
        self.old_payment_service = old_payment_service

    def process_payment(self, amount):
        self.old_payment_service.make_transaction(amount)

# Uso do adaptador
def main():
    old_service = OldPaymentService()
    adapter = PaymentAdapter(old_service)
    
    # A aplicação usa a interface PaymentProcessor
    adapter.process_payment(100)

if __name__ == "__main__":
    main()
