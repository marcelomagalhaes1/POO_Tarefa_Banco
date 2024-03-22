/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author valdi
 */


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Conta> contas = new ArrayList<>();

        while (true) {
            System.out.println("Escolha uma opcao: ");
            System.out.println("1 - Criar nova conta");
            System.out.println("2 - Exibir saldo");
            System.out.println("3 - Sacar");
            System.out.println("4 - Depositar");
            System.out.println("5 - Transferir");
            System.out.println("6 - Exit");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> criarNovaConta(scanner, contas);
                case 2 -> exibirSaldo(scanner, contas);
                case 3 -> sacar(scanner, contas);
                case 4 -> depositar(scanner, contas);
                case 5 -> transferir(scanner, contas);
                case 6 -> {System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida!!");
            }
        }
    }

    private static void criarNovaConta(Scanner scanner, ArrayList<Conta> contas) {
        System.out.println("Informe o nome do titular: ");
        String nome = scanner.next();

        System.out.println("Informe o CPF do titular: ");
        String cpf = scanner.next();

        Titular titular = new Titular(nome, cpf);

        System.out.println("Informe o número da conta: ");
        String numeroConta = scanner.next();

        System.out.println("Informe o número da agência: ");
        int numeroAgencia = scanner.nextInt();

        System.out.println("Informe o saldo inicial: ");
        double saldoInicial = scanner.nextDouble();

        Conta novaConta = new Conta(numeroConta, numeroAgencia, titular, saldoInicial);
        contas.add(novaConta);

        System.out.println("Conta criada com sucesso!!");
    }

    private static void exibirSaldo(Scanner scanner, ArrayList<Conta> contas) {
        System.out.println("Informe o número da conta: ");
        String numeroConta = scanner.next();

        for (Conta conta : contas) {
            if (conta.getNumero().equals(numeroConta)) {
                System.out.println("Saldo da conta " + numeroConta + ": " + conta.getSaldo());
                return;
            }
        }
        System.out.println("Conta não encontrada!!");
    }

    private static void sacar(Scanner scanner, ArrayList<Conta> contas) {
        System.out.println("Informe o número da conta: ");
        String numeroConta = scanner.next();

        for (Conta conta : contas) {
            if (conta.getNumero().equals(numeroConta)) {
                System.out.println("Informe o valor a ser sacado: ");
                double valor = scanner.nextDouble();
                if (conta.sacar(valor)) {
                    System.out.println("Saque realizado com sucesso!!");
                } else {
                    System.out.println("Saldo insuficiente!!");
                }
                return;
            }
        }

        System.out.println("Conta não encontrada!!");
    }

    private static void depositar(Scanner scanner, ArrayList<Conta> contas) {
        System.out.println("Informe o número da conta: ");
        String numeroConta = scanner.next();

        for (Conta conta : contas) {
            if (conta.getNumero().equals(numeroConta)) {
                System.out.println("Informe o valor a ser depositado: ");
                double valor = scanner.nextDouble();
                conta.depositar(valor);
                System.out.println("Depósito realizado!!");
                return;
            }
        }

        System.out.println("Conta não encontrada.");
    }

    private static void transferir(Scanner scanner, ArrayList<Conta> contas) {
        System.out.println("Informe o número da conta: ");
        String numeroContaOrigem = scanner.next();

        System.out.println("Informe o número da outra conta: ");
        String numeroContaDestino = scanner.next();

        Conta origem = null;
        Conta destino = null;

        for (Conta conta : contas) {
            if (conta.getNumero().equals(numeroContaOrigem)) {
                origem = conta;
            }
            if (conta.getNumero().equals(numeroContaDestino)) {
                destino = conta;
            }
        }

        if (origem != null && destino != null) {
            System.out.println("Informe o valor: ");
            double valor = scanner.nextDouble();
            if (origem.transferir(valor, destino)) {
                System.out.println("Transferência realizada!!");
            } else {
                System.out.println("Saldo insuficiente!!");
            }
        } else {
            System.out.println("Conta não encontrada!!");
        }
    }
}
