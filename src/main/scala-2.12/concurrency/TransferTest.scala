package concurrency

import akka.actor.{ActorSystem, Props}
import concurrency.actors.BankAccount.Deposit
import concurrency.actors.{BankAccount, Transfer, AccountTransferActor}

/**
  * Created by bpant on 08/02/17.
  */
object TransferTest extends App {

  val system = ActorSystem("ActorSystem")
  val accountA = system.actorOf(Props[BankAccount], "accountA")
  accountA ! Deposit(500)
  val accountB = system.actorOf(Props[BankAccount], "accountB")
  val transferATOB = system.actorOf(AccountTransferActor.props(accountA, accountB))
  transferATOB ! Transfer(300)
}

