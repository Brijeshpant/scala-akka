package actor.supervisor

import akka.actor.SupervisorStrategy.{Escalate, Restart, Stop}
import akka.actor.{Actor, AllForOneStrategy, OneForOneStrategy, Props, SupervisorStrategy}

/**
  * Created by bpant on 09/02/17.
  */
case class ThrowException()

class Supervisor extends Actor {
  val child1 = context.actorOf(Props[ChildActor], "child1")
  val child2 = context.actorOf(Props[ChildActor], "child2")


  override def supervisorStrategy: SupervisorStrategy =
    OneForOneStrategy() {
      //AllForOneStrategy
      case ex: Exception => Restart
    }

  override def receive: Receive = {

    case ThrowException => child1 ! ThrowException


  }
}
