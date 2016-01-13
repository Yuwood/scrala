package com.gaocegege.scrala.core.spider

import com.gaocegege.scrala.core.common.response.impl.HttpResponse
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory
import com.gaocegege.scrala.core.middleware.filter.impl.DefaultFilter
import com.gaocegege.scrala.core.middleware.filter.Filter

trait Spider {
  def startUrl: List[String]
  def filter: Filter = new DefaultFilter
  def parse(response: HttpResponse): Unit
  def begin(): Unit
  def request(url: String, callback: (HttpResponse) => Unit): Unit

  def poison(): Unit

  val logger = Logger(LoggerFactory.getLogger("spider"))
  private[this] var realWorker = 4
  def workerCount: Int = realWorker;
  def workerCount_=(v: Int) { realWorker = v; };

}
