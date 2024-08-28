public class AdapterPatternExample {

    // Interface PagamentoProcessor
    interface PagamentoProcessor {
        void processarPagamento(double quantia);
    }

    // Implementação existente com PayPalProcessor
    static class PayPalProcessor implements PagamentoProcessor {
        @Override
        public void processarPagamento(double quantia) {
            System.out.println("Pagamento de " + quantia + " processado via PayPal.");
        }
    }

    // Nova classe Stripe com interface diferente
    static class Stripe {
        public void makePayment(double amount) {
            System.out.println("Pagamento de " + amount + " processado via Stripe.");
        }
    }

    // Classe StripeAdapter que implementa PagamentoProcessor
    static class StripeAdapter implements PagamentoProcessor {
        private Stripe stripe;

        public StripeAdapter(Stripe stripe) {
            this.stripe = stripe;
        }

        @Override
        public void processarPagamento(double quantia) {
            stripe.makePayment(quantia); 
        }
    }

    // Método main para testar o padrão Adapter
    public static void main(String[] args) {
        // Usando o processador PayPal
        PagamentoProcessor paypal = new PayPalProcessor();
        paypal.processarPagamento(50.0); // Saída: "Pagamento de 50.0 processado via PayPal."

        // Usando o processador Stripe através do adaptador
        Stripe stripe = new Stripe();
        PagamentoProcessor stripeAdapter = new StripeAdapter(stripe);
        stripeAdapter.processarPagamento(75.0); // Saída: "Pagamento de 75.0 processado via Stripe."
    }
}
