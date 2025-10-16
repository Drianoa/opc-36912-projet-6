import {Component, input, ChangeDetectionStrategy} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PostResponse} from "../../core/interfaces/postResponse.interface";
import {RouterLink} from "@angular/router";

/**
 * Composant de prévisualisation d'article utilisant les signaux Angular 20+
 * Affiche un résumé d'article avec troncature du contenu à 5 lignes maximum
 */
@Component({
  selector: 'app-article-preview',
  imports: [CommonModule, RouterLink],
  templateUrl: './post-preview.component.html',
  styleUrl: './post-preview.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class PostPreviewComponent {
  /** Signal d'entrée pour les données du post */
  post = input.required<PostResponse>();
}
