#include "src/controller/headers/Controller.hpp"
#include "src/controller/headers/Configs.hpp"
#include "src/view/gui/headers/ViewUI.hpp"
#include <QApplication>



int main(int argc, char ** argv)
{
    QApplication core(argc, argv);
    dblk::Diaballik model{};
    dblk::ViewUI view;
    dblk::Controller controller(model, view);

    controller.init();
    return core.exec();
}

