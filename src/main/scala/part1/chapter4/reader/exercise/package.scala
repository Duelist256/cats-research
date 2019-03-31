package part1.chapter4.reader

import cats.data.Reader

package object exercise {
  type DbReader[A] = Reader[Db, A]
}