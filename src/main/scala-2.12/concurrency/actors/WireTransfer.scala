package concurrency.actors

import akka.actor.{Actor, ActorRef, Stash}

/**
  * Created by bpant on 08/02/17.
  */
object WireTransfer {

  case class Transfer(from: ActorRef, to: ActorRef, amount: Int)

  case object Done

  case object Failed

}

class WireTransfer extends Actor with Stash {

  import WireTransfer._

  def receive = {
    case Transfer(from, to, amount) =>
      from ! BankAccount.Withdraw(amount)
      context.become(behaviourReceivingFromMessage(to, amount, sender))
  }

  def behaviourReceivingFromMessage(to: ActorRef, amount: Int, customer: ActorRef): Receive = {
    case BankAccount.Done =>
      to ! BankAccount.Deposit(amount)
      context.become(behaviourReceivingToMessage(customer))
    case BankAccount.Failed =>
      customer ! Failed
      context.stop(self)
  }

  def behaviourReceivingToMessage(customer: ActorRef): Receive = {
    case BankAccount.Done =>
      customer ! Done
      unstashAll()
      context.stop(self)
  }
}