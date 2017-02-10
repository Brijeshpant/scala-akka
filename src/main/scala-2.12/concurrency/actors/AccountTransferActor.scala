package concurrency.actors

import akka.actor.{Actor, ActorRef, Props, Stash, Terminated}

/**
  * Created by bpant on 08/02/17.
  */
case class Transfer(amount: Int)

object AccountTransferActor {
  def props(accountA: ActorRef, accountB: ActorRef): Props = Props(new AccountTransferActor(accountA, accountB))
}

class AccountTransferActor(accountA: ActorRef, accountB: ActorRef) extends Actor with Stash {
  def receive = {
    case Transfer(amount) => transfer(amount)
    case _ => stash
  }

  def transfer(amount: Int): Unit = {

    val transaction = context.actorOf(Props[WireTransfer], "transfer")

    transaction ! WireTransfer.Transfer(accountA, accountB, amount)

    context.become({
      case WireTransfer.Done =>
        println("transaction completed")
        unstashAll
        context.stop(self)
      case WireTransfer.Failed =>
        println("failed")
        unstashAll
        context.stop(self)
    })

  }
}