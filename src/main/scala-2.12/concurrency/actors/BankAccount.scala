package concurrency.actors

import akka.actor.Actor

/**
  * Created by bpant on 08/02/17.
  */
object BankAccount {

  case class Deposit(amount: Int) {
    require(amount > 0)
  }

  case class Withdraw(amount: Int) {
    require(amount > 0)
  }

  case object Done

  case object Failed

}

class BankAccount extends Actor {

  import BankAccount._

  var balance: Int = 0

  def receive = {
    case Deposit(amount) =>
      balance += amount
      sender ! Done

    case Withdraw(amount) if amount <= balance =>
      balance -= amount
      sender ! Done

    case _ => sender ! Failed
  }

}