package windowing

import java.awt.{FlowLayout, Font}
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel


class Output(initString: String) extends JFrame {

  build()

  var panel = new JPanel()
  var jlabel = new JLabel(initString)
  val myFont = new Font("Courier", Font.PLAIN, 12)
  jlabel.setFont(myFont)
  panel.setLayout(new FlowLayout())
  panel.add(jlabel)
  this.setContentPane(panel)
  this.setVisible(true)


  def changeText(text: String): Unit = {
    jlabel.setText(text)
  }

  private def build(): Unit = {
    setTitle("Fenêtre qui affiche du texte") //On donne un titre à l'application
    setSize(320, 240) //On donne une taille à notre fenêtre
    setLocationRelativeTo(null) //On centre la fenêtre sur l'écran
    setResizable(true) //On permet le redimensionnement
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) //On dit à l'application de se fermer lors du clic sur la croix
  }


}