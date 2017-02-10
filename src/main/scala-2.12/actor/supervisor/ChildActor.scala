package actor.supervisor

import akka.actor.Actor

/**
  * Created by bpant on 09/02/17.
  */

class ChildActor extends Actor {


  @scala.throws[Exception](classOf[Exception])
  override def postStop(): Unit = {
    println(s"Message postStop for $self")

  }

  @scala.throws[Exception](classOf[Exception])
  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    println(s"Message preRestart for $self")
  }

  @scala.throws[Exception](classOf[Exception])
  override def postRestart(reason: Throwable): Unit = {
    self ! s"message on restart of $self"
  }

  override def receive: Receive = {
    case s: String => println(s"message received $s")
    case ThrowException => throw new Exception("Some exception")
  }
}
