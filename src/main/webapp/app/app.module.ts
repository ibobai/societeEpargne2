import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { BriefGroupeJHipsterSharedModule } from 'app/shared/shared.module';
import { BriefGroupeJHipsterCoreModule } from 'app/core/core.module';
import { BriefGroupeJHipsterAppRoutingModule } from './app-routing.module';
import { BriefGroupeJHipsterHomeModule } from './home/home.module';
import { BriefGroupeJHipsterEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    BriefGroupeJHipsterSharedModule,
    BriefGroupeJHipsterCoreModule,
    BriefGroupeJHipsterHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    BriefGroupeJHipsterEntityModule,
    BriefGroupeJHipsterAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent],
})
export class BriefGroupeJHipsterAppModule {}
